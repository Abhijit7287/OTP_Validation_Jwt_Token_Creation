# OTP_Send_Validate_JWT_Token

>>>These are the Six main features of this project

>>1. OTP Generation & Validation:

Users must validate their email address by entering the correct OTP.
An OTP service generates and sends OTPs via email.
Validated email addresses are stored in the database.

>>2. User Registration:

The project allows users to register by providing their username, password, gender, and email address (AddUser DTO).
The registration process sends an OTP (One-Time Password) to the user's email address for verification.
Upon successful authentication, the system issues a JWT (JSON Web Token) for user authorization.

>>3. User Authentication / User Log in:

Registered users can log in using their username and password (RequestLogin DTO).
Upon successful authentication, the system issues a JWT (JSON Web Token) for user authorization.

>>4. JWT-Based Authentication:

A custom JWT authentication filter (JwtAuthenticationFilter) ensures that incoming requests are authenticated based on the JWT provided in the "Authorization" header.
JWT tokens are used for securing access to various endpoints.

>>5.User Profile Retrieval:

Users can retrieve their user profile, including their first name, last name, and gender (UserProfile DTO).

>>6.Spring Security Configuration:

Spring Security is configured to handle user authentication and authorization.
Certain endpoints are configured to allow unauthenticated access, while others require authentication.
Session management is set to be stateless.
