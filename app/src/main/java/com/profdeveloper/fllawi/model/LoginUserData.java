package com.profdeveloper.fllawi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginUserData implements Serializable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("permissions")
        @Expose
        private List<Object> permissions = null;
        @SerializedName("last_login")
        @Expose
        private LoginRequestResponse.LastLogin lastLogin;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("company_name")
        @Expose
        private String companyName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("birthday")
        @Expose
        private String birthday;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("id_proof")
        @Expose
        private String idProof;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("position")
        @Expose
        private String position;
        @SerializedName("country_id")
        @Expose
        private int countryId;
        @SerializedName("preferred_payment_id")
        @Expose
        private int preferredPaymentId;
        @SerializedName("preferred_currency_id")
        @Expose
        private int preferredCurrencyId;
        @SerializedName("preferred_language_id")
        @Expose
        private String preferredLanguageId;
        @SerializedName("is_active")
        @Expose
        private int isActive;
        @SerializedName("roles")
        @Expose
        private List<LoginRequestResponse.Role> roles = null;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<Object> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<Object> permissions) {
            this.permissions = permissions;
        }

        public LoginRequestResponse.LastLogin getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(LoginRequestResponse.LastLogin lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdProof() {
            return idProof;
        }

        public void setIdProof(String idProof) {
            this.idProof = idProof;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        public int getPreferredPaymentId() {
            return preferredPaymentId;
        }

        public void setPreferredPaymentId(int preferredPaymentId) {
            this.preferredPaymentId = preferredPaymentId;
        }

        public int getPreferredCurrencyId() {
            return preferredCurrencyId;
        }

        public void setPreferredCurrencyId(int preferredCurrencyId) {
            this.preferredCurrencyId = preferredCurrencyId;
        }

        public String getPreferredLanguageId() {
            return preferredLanguageId;
        }

        public void setPreferredLanguageId(String preferredLanguageId) {
            this.preferredLanguageId = preferredLanguageId;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public List<LoginRequestResponse.Role> getRoles() {
            return roles;
        }

        public void setRoles(List<LoginRequestResponse.Role> roles) {
            this.roles = roles;
        }

    }