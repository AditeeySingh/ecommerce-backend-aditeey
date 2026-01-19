# 🧪 Minimal E-Commerce Backend API (Spring Boot)

This project is a **minimal e-commerce backend system** built using **Spring Boot 4.x** and **MongoDB** as part of an in-class graded assignment.(Razorpay integration was not used due to personal account (lack of Pan card) constraints)

It demonstrates core backend concepts such as:
- RESTful API design
- Database modeling with MongoDB
- Business logic for cart and orders
- Asynchronous payment handling using a **Mock Payment Service**
- Webhook-based order status updates
- API testing using Postman

---

## 🎯 Objective

The goal of this project is to build a backend system where:

- Products can be created and listed
- Users can add products to a cart
- Orders can be created from the cart
- Payments can be initiated (Mock Payment Service)
- Order status updates automatically after payment
- All APIs are tested using Postman

---

## 🏗️ Architecture Overview

**Client:** Postman  
**Backend:** Spring Boot (Port 8080)  
**Database:** MongoDB  
**Payment:** Mock Payment Service (Webhook-based)

Flow:
1. Client calls backend APIs
2. Backend creates orders and payments
3. Mock payment simulates processing delay
4. Webhook updates payment and order status

---

## 📊 Database Entities

The system consists of the following entities:

- **User** – represents a customer
- **Product** – items available for purchase
- **CartItem** – items added to a user's cart
- **Order** – created from cart items
- **OrderItem** – individual items within an order
- **Payment** – payment details for an order

Relationships:
- One User → Many CartItems
- One User → Many Orders
- One Order → Many OrderItems
- One Order → One Payment

---

## 📁 Project Structure

```
com.example.ecommerceAditeey
│
├── controller
│   ├── ProductController.java
│   ├── CartController.java
│   ├── OrderController.java
│   └── PaymentController.java
│
├── service
│   ├── ProductService.java
│   ├── CartService.java
│   ├── OrderService.java
│   └── PaymentService.java
│
├── repository
│   ├── ProductRepository.java
│   ├── CartItemRepository.java
│   ├── OrderRepository.java
│   ├── OrderItemRepository.java
│   └── PaymentRepository.java
│
├── model
│   ├── User.java
│   ├── Product.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Payment.java
│
├── dto
│   ├── AddToCartRequest.java
│   ├── CreateOrderRequest.java
│   ├── PaymentRequest.java
│   └── PaymentWebhookRequest.java
│
├── webhook
│   └── PaymentWebhookController.java
│
└── EcommerceAditeeyApplication.java
```

---

## 📌 API Endpoints

### 🛒 Product APIs

- **POST** `/api/products` – Create a product
- **GET** `/api/products` – List all products

### 🛒 Cart APIs

- **POST** `/api/cart/add` – Add product to cart
- **GET** `/api/cart/{userId}` – View user cart
- **DELETE** `/api/cart/{userId}/clear` – Clear cart

### 📦 Order APIs

- **POST** `/api/orders` – Create order from cart
- **GET** `/api/orders/{orderId}` – View order details

### 💳 Payment APIs (Mock Service)

- **POST** `/api/payments/create` – Initiate payment
- **POST** `/api/webhooks/payment` – Payment webhook

---

## 🔄 Payment Flow (Mock Service)

1. Payment is created with status `PENDING`
2. Mock service waits for 3 seconds
3. Webhook is triggered automatically
4. Payment status → `SUCCESS`
5. Order status → `PAID`

This simulates real-world payment gateways like Razorpay.

---

## 🧪 Testing

All APIs were tested using **Postman** with:
- Environment variables (`baseUrl`, `userId`, `productId`, `orderId`)
- Saved requests for all endpoints
- Full end-to-end purchase flow tested

---

## 📝 How to Run the Project

### Prerequisites
- Java 17+
- MongoDB (running on localhost:27017)
- Maven

### Steps

1. Clone or unzip the project
2. Open in IntelliJ IDEA
3. Ensure MongoDB is running
4. Run `EcommerceAditeeyApplication`
5. Test APIs using Postman

---

## 🎓 Key Concepts Demonstrated

- RESTful API design
- MongoDB with Spring Data
- Service-layer business logic
- Cart → Order conversion
- Stock management
- Webhook-based asynchronous processing

---

## ✅ Assignment Status

✔ All mandatory requirements implemented  
✔ Mock payment service used (as allowed)  
✔ Order status updates via webhook  
✔ APIs tested with Postman  

---

## 📌 Note

Razorpay integration was not used due to personal account (lack of Pan card) constraints. A mock payment service was implemented instead, which demonstrates the same architectural and logical concepts required by the assignment.

---

## 👨‍💻 Author

**Aditeey Singh Jadon**  
B.Sc. Computer Science

