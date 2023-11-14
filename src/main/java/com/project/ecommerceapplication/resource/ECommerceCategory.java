package com.project.ecommerceapplication.resource;

public enum ECommerceCategory {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    BOOKS("Books"),
    HOME_APPLIANCES("Home Appliances"),
    SPORTS("Sports"),
    BEAUTY("Beauty"),
    TOYS("Toys"),
    FURNITURE("Furniture");

    private final String displayName;

    ECommerceCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
