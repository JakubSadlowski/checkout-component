# Checkout Component REST Service

Checkout REST Service implemented using Java and Spring for calculating total price of the items in cart.

---

Service design was first drawn in draw.io and created file is available in properties folder.
```
src/main/resources/readme/checkout-component-design.drawio
```
Below is an image from draw.io of the component checkout service.

<img src="src/main/resources/readme/RestAPI.png" alt="Link to" title="Link to" />

---

## Database

### Tables
- **`item`**: Table for storing unique items with normal price.
- **`item_discounts`**: Table for storing special price of every item and required quantity for that special price to be applied.
- **`bundle_discounts`**: Table for storing bundles consisting of two items and their discount.

## Service


### Endpoints
| POST | /checkout-component/checkout |
|------|------------------------------|
