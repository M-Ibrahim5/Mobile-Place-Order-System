$(document).ready(function () {
    const cartId = 1; // Assuming cartId is known

    // Fetch cart details from the API
    function loadCart() {
        $.ajax({
            url: `/cart/${cartId}`, // Fetch cart items
            method: 'GET',
            success: function (response) {
                // Ensure the response contains a valid cart structure
                if (!response || !response.items || response.items.length === 0) {
                    $('#cart-items-body').html('<tr><td colspan="4">Your cart is empty.</td></tr>'); // Display empty message in the table
                    $('#total-price').text('0.00'); // Reset total price
                    return;
                }

                const cartItems = response.items; // Extract items array
                const cartTotal = response.total || 0; // Extract total or default to 0

                // Clear existing items in the table
                const cartItemsBody = $('#cart-items-body');
                cartItemsBody.empty();

                // Loop through cart items and add rows to the table
                cartItems.forEach(function (item) {
                    // Ensure the item and product exist
                    if (!item || !item.product) {
                        console.warn('Invalid item or missing product:', item);
                        return;
                    }

                    const product = item.product;
                    const linePrice = item.linePrice || (product.price * item.quantity); // Calculate if missing
                    const cartRow = `
                        <tr>
                            <td>${product.name}</td>
                            <td>RM ${product.price.toFixed(2)}</td>
                            <td>${item.quantity}</td>
                            <td>RM ${linePrice.toFixed(2)}</td>
                        </tr>
                    `;
                    cartItemsBody.append(cartRow);
                });

                // Update total price
                $('#total-price').text(cartTotal.toFixed(2));
            },
            error: function (xhr) {
                console.error('Error loading cart:', xhr.responseText);
                alert('An error occurred while loading the cart. Please try again.');
            }
        });
    }

    // Load cart data when the page loads
    loadCart();

    // Redirect to the homepage when "Continue Shopping" button is clicked
    $('#continue-shopping-button').on('click', function () {
        window.location.href = '/index.html'; // Adjust this to match your actual homepage
    });

    // Redirect to the checkout page when "Proceed to Checkout" button is clicked
    $('#checkout-button').on('click', function () {
        window.location.href = '/checkout.html'; // Adjust this to match your actual checkout page
    });
});
