$(document).ready(function() {
    const cartId = 1; // Assuming you store the cartId in localStorage or similar

    // Fetch cart details from the API
    function loadCart() {
        $.ajax({
            url: `/cart/${cartId}`, // Fetch cart items (updated to include line price)
            method: 'GET',
            success: function(response) {
                const cartItems = response.items; // Assuming the response contains items
                const cartTotal = response.total; // Assuming the response includes the total amount

                // Clear existing items
                const cartItemsBody = $('#cart-items-body');
                cartItemsBody.empty();

                // Loop through cart items and create table rows
                cartItems.forEach(function(item) {
                    const linePrice = item.linePrice; // Get line price directly from the response
                    const cartRow = `
                        <tr>
                            <td>${item.product.name}</td>
                            <td>RM ${item.product.price}</td>
                            <td>${item.quantity}</td>
                            <td>RM ${linePrice.toFixed(2)}</td>
                        </tr>
                    `;
                    cartItemsBody.append(cartRow);
                });

                // Update total price
                $('#total-price').text(cartTotal.toFixed(2));
            },
            error: function() {
                alert('Error loading cart');
            }
        });
    }

    // Load cart data when the page loads
    loadCart();

    // Continue shopping button functionality (navigate back to product page or home page)
    $('#continue-shopping-button').on('click', function() {
        window.location.href = '/index.html'; // Redirect to homepage or product listing
    });

    // Checkout button functionality (you can link to checkout page here)
    $('#checkout-button').on('click', function() {
        window.location.href = '/checkout.html'; // Redirect to checkout page
    });
});
