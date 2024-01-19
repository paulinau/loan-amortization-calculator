# Loan Amortization Service

This Java project consists of 5 microservices that collectively manage the loan amortization process. These microservices are responsible for generating the amortization table, updating it as the user makes monthly payments, and handling the corresponding credit card charges.

## Microservices Overview

1. **Amortization Table Generator:** Creates the initial amortization table based on the provided loan details.

2. **Payment Processor:** Manages the user's monthly payments and updates the amortization table accordingly.

3. **Credit Card Charge Service:** Handles the charging process to the user's credit card based on the amortization schedule.

4. **User Profile Management:** Manages user profiles, storing necessary information for the loan and payment processes.

5. **Notification Service:** Sends notifications to users about upcoming payments, successful transactions, and other relevant updates.

## API Endpoints

- **Generate Amortization Table:**
  - Endpoint: `/api/amortization/generate`
  - Method: `POST`
  - Input: [Sample Request](https://desktop.postman.com/?desktopVersion=10.20.0&webVersion=10.20.10-ui-231204-0510&userId=20132851&teamId=4276379&entity=request-27f73a88-a9c6-4b02-9043-f2583757a4c1)

- **Make Monthly Payment:**
  - Endpoint: `/api/payment/make`
  - Method: `POST`
  - Input: [Sample Request](https://desktop.postman.com/?desktopVersion=10.20.0&webVersion=10.20.10-ui-231204-0510&userId=20132851&teamId=4276379&entity=request-7deb8837-7b33-40d2-960a-199ee6456823)

## Getting Started

1. Clone this repository.
2. Configure and deploy each microservice.
3. Utilize the provided API endpoints to generate the amortization table and manage monthly payments.

## Additional Notes

- For detailed API documentation, refer to each microservice's respective directory.
- Sample input data for generating the amortization table and making monthly payments can be found in the provided Postman requests.

Feel free to explore and contribute to the project! If you have any questions or suggestions, please open an issue or submit a pull request.

---

**Note:** Replace the sample API endpoints and input data links with the actual URLs relevant to your project.
