package com.stonesoup;

import com.parse.ParseClassName;
import com.parse.ParseObject;

	@ParseClassName("User")
	public class Users extends ParseObject {

        public Users() {
        }

        public String getUsername() {
                return getString("username");
        }

        public void setUsername(String username) {
                put("username", username);
        }

}