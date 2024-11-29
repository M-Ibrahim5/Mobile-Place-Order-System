$(document).ready(function () {
    const cartId = 1; // Assuming cartId is known

    // Load cart details for review
    function loadCartDetails() {
        $.ajax({
            url: `/cart/${cartId}`, // Fetch cart details
            method: 'GET',
            success: function (response) {
                const checkoutDetails = $('#checkout-details');
                checkoutDetails.empty(); // Clear existing details

                if (!response.items || response.items.length === 0) {
                    checkoutDetails.html('<p>Your cart is empty. Add items before proceeding to checkout.</p>');
                    $('#place-order-button').prop('disabled', true); // Disable Place Order button
                    return;
                }

                let summaryHtml = '<h2>Order Summary</h2>';
                summaryHtml += '<ul>';
                response.items.forEach(function (item) {
                    summaryHtml += `
                        <li>
                            ${item.product.name} (x${item.quantity}) - RM ${item.linePrice.toFixed(2)}
                        </li>
                    `;
                });
                summaryHtml += `</ul><p><strong>Total: RM ${response.total.toFixed(2)}</strong></p>`;

                checkoutDetails.html(summaryHtml);
            },
            error: function (xhr) {
                alert('Error loading cart details: ' + xhr.responseText);
            }
        });
    }

    // Place order
    $('#place-order-button').on('click', function () {
        $.ajax({
            url: `/orders/place/${cartId}`, // Endpoint to place the order
            method: 'POST',
            success: function () {
                alert('Order placed successfully!');
                window.location.href = '/index.html'; // Redirect to homepage or order confirmation page
            },
            error: function (xhr) {
                alert('Error placing order: ' + xhr.responseText);
            }
        });
    });

    // Back to cart
    $('#back-to-cart-button').on('click', function () {
        window.location.href = '/cart.html'; // Redirect back to the cart page
    });

    // Load cart details when the page loads
    loadCartDetails();
});
