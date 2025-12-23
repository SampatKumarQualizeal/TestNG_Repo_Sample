
    /**
     * Workflow: Attempt to create a contact with an invalid email and return whether the error message is displayed.
     * This method enters all required details, enters an invalid email, attempts to save, and checks for error message presence.
     *
     * @param firstName First Name
     * @param lastName Last Name
     * @param phoneNumber Phone Number
     * @param company Company Name
     * @param invalidEmail Invalid Email Address (e.g., 'invalid-email')
     * @param emailDescription Email Description (e.g., Personal, Business)
     * @param category Category to select (e.g., Lead, Customer, Contact, Affiliate)
     * @return true if invalid email error message is displayed, false otherwise
     */
    public boolean attemptToCreateContactWithInvalidEmail(String firstName, String lastName, String phoneNumber, String company, String invalidEmail, String emailDescription, String category) {
        createNewContact(firstName, lastName, phoneNumber, company, invalidEmail, emailDescription, category);
        // Error message locator for invalid email format (placeholder, update if actual locator is available)
        By invalidEmailErrorMessage = By.xpath("//div[contains(@class, 'error') and contains(text(), 'invalid email')]");
        try {
            // Wait briefly for error message to appear (simple sleep, replace with explicit wait if available in framework)
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        return driver.findElements(invalidEmailErrorMessage).size() > 0;
    }
