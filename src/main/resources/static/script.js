$(document).ready(function() {
    // Fetch products from the API and display them on the homepage
    function loadProducts() {
        $.ajax({
            url: '/products', // API endpoint to fetch products
            method: 'GET',
            success: function(response) {
                const productList = $('#product-list');
                productList.empty(); // Clear the product list

                // Loop through the products and create HTML cards for each
                response.forEach(function(product) {
                    const productCard = `
                        <div class="product-card">
                            <h3>${product.name}</h3>
                            <p>Price: ${product.price}</p>
                            <button class="add-to-cart" data-id="${product.id}">Add to Cart</button>
                        </div>
                    `;
                    productList.append(productCard);
                });
            },
            error: function() {
                alert('Error loading products');
            }
        });
    }

    // Add product to cart
    $(document).on('click', '.add-to-cart', function() {
        const productId = $(this).data('id');
        const quantity = 1; // Can be enhanced by adding a quantity field if needed
        const cartId = 1; // Assuming you store the cartId in localStorage

        if (!cartId) {
            alert('Cart ID is missing. Please log in or start a new cart.');
            return;
        }

        // Pass productId and quantity as URL parameters
        $.ajax({
            url: `/cart/${cartId}/add?productId=${productId}&quantity=${quantity}`, // Add parameters to the URL
            method: 'POST',
            success: function() {
                alert('Product added to cart');
            },
            error: function() {
                alert('Error adding product to cart');
            }
        });
    });

    // Load products when the page loads
    loadProducts();

    // View Cart button functionality
    $('#viewCartButton').on('click', function() {
        window.location.href = '/cart.html'; // Redirect to the cart page
    });
});
