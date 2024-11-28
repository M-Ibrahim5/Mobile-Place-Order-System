// Fetch and display products
fetch('/products')
    .then(response => response.json())
    .then(data => {
        const products = data._embedded.products; // Adjusted for HATEOAS response
        const productList = document.getElementById('product-list');
        products.forEach(product => {
            const div = document.createElement('div');
            div.className = 'product-card';
            div.innerHTML = `
                <h3>${product.name}</h3>
                <p>Type: ${product.type}</p>
                <p>Price: RM${product.price.toFixed(2)}</p>
                <p>Stock: ${product.quantity}</p>
                <button onclick="addToCart(${product.id}, 1)">Add to Cart</button>
            `;
            productList.appendChild(div);
        });
    })
    .catch(error => console.error('Error fetching products:', error));

// Add product to cart
function addToCart(productId, quantity) {
    fetch(`/cart/1/add?productId=${productId}&quantity=${quantity}`, { method: 'POST' })
        .then(() => {
            alert('Product added to cart!');
            loadCart();
        })
        .catch(error => console.error('Error adding to cart:', error));
}

// Fetch and display cart
function loadCart() {
    fetch('/cart/1')
        .then(response => response.json())
        .then(cart => {
            const cartDiv = document.getElementById('cart');
            cartDiv.innerHTML = ''; // Clear existing items
            cart.items.forEach(item => {
                const div = document.createElement('div');
                div.className = 'cart-item';
                div.innerHTML = `
                    <h3>${item.product.name}</h3>
                    <p>Quantity: ${item.quantity}</p>
                `;
                cartDiv.appendChild(div);
            });
        })
        .catch(error => console.error('Error fetching cart:', error));
}

// Load cart on page load
loadCart();
