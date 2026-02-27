class ApiConstants {
  static const String baseUrl =
      "http://10.0.2.2:8081/api/v1"; // For Android Emulator
  // static const String baseUrl = "http://localhost:8080/api/v1"; // For Chrome/Web/iOS

  static const String register = "$baseUrl/auth/register";
  static const String verifyOtp = "$baseUrl/auth/verify-otp";
  static const String resendOtp = "$baseUrl/auth/resend-otp";
  static const String login = "$baseUrl/auth/login";
  static const String profile = "$baseUrl/users/profile";
  static const String forgotPassword = "$baseUrl/auth/forgot-password";
  static const String resetPassword = "$baseUrl/auth/reset-password";
}
