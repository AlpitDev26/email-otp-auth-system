import 'dart:convert';
import 'package:http/http.dart' as http;
import '../utils/api_constants.dart';

class ApiService {
  
  // 1. Register
  static Future<http.Response> register(String username, String email, String password) async {
    final response = await http.post(
      Uri.parse(ApiConstants.register),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "username": username,
        "email": email,
        "password": password,
      }),
    );
    return response;
  }

  // 2. Verify OTP
  static Future<http.Response> verifyOtp(String email, String otp) async {
    final response = await http.post(
      Uri.parse(ApiConstants.verifyOtp),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "email": email,
        "otp": otp,
      }),
    );
    return response;
  }

  // 3. Resend OTP
  static Future<http.Response> resendOtp(String email) async {
    // Note: backend uses @RequestParam, so we send as query parameter
    final response = await http.post(
      Uri.parse("${ApiConstants.resendOtp}?email=$email"),
    );
    return response;
  }

  // 4. Login
  static Future<http.Response> login(String email, String password) async {
    final response = await http.post(
      Uri.parse(ApiConstants.login),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({
        "email": email,
        "password": password,
      }),
    );
    return response;
  }

  // 5. Get Profile (Protected)
  static Future<http.Response> getProfile(String token) async {
    final response = await http.get(
      Uri.parse(ApiConstants.profile),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer $token",
      },
    );
    return response;
  }
}
