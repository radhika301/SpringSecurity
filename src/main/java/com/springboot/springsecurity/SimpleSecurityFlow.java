package com.springboot.springsecurity;

import java.awt.Desktop;
import java.net.URI;

public class SimpleSecurityFlow {

    public static void main(String[] args) {
        // Simulate incoming request
        String url = "http://localhost:8080/hello"; // URL to test
        boolean userLoggedIn = true;                 // simulate logged-in user
        String userRole = "USER";                    // simulate user role

        try {
            // 1️⃣ Check URL rules
            if (url.endsWith("/public")) {
                // public URL → allow access
                System.out.println("URL is public → access granted to controller");
                openInBrowser(url);

            } else {
                // protected URL → requires login
                System.out.println("URL is protected → check authentication");

                // 2️⃣ Check authentication
                if (userLoggedIn) {
                    System.out.println("User is logged in → check authorization");

                    // 3️⃣ Check authorization (roles)
                    if (userRole.equals("ADMIN") || userRole.equals("USER")) {
                        System.out.println("User role allowed → proceed to controller");
                        openInBrowser(url);
                    } else {
                        System.out.println("User role NOT allowed → 403 Forbidden");
                    }

                } else {
                    System.out.println("User not logged in → 401 Unauthorized (show login)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to open URL in default browser
    private static void openInBrowser(String url) throws Exception {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(url));
            System.out.println("Opening URL in browser: " + url);
        } else {
            System.out.println("Desktop not supported. Cannot open browser.");
        }
    }
}
