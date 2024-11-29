$(document).ready(function() {
    const cartId = 1; // Assuming you store the cartId in localStorage or similar

    // Fetch cart details from the API
    function loadCart() {
        $.ajax({
            url: `/cart/${cartId}`, // Fetch cart items
            method: 'GET',
            success: function(response) {
                const cartItems = response.items; // Extract items array
                const cartTotal = response.total; // Extract total

                // Clear existing items in the table
                const cartItemsBody = $('#cart-items-body');
                cartItemsBody.empty();

                // Loop through cart items and add rows to the table
                cartItems.forEach(function(item) {
                    const linePrice = item.linePrice; // Get line price from response
                    const cartRow = `
                        <tr>
                            <td>${item.product.name}</td>
                            <td>RM ${item.product.price.toFixed(2)}</td>
                            <td>${item.quantity}</td>
                            <td>RM ${linePrice.toFixed(2)}</td>
                        </tr>
                    `;
                    cartItemsBody.append(cartRow);
                });

                // Update total price
                $('#total-price').text(cartTotal.toFixed(2));
            },
            error: function(xhr) {
                alert('Error loading cart: ' + xhr.responseText);
            }
        });
    }

    // Load cart data when the page loads
    loadCart();
});
