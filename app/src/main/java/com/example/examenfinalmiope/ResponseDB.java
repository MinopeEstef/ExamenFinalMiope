package com.example.examenfinalmiope;

public class ResponseDB {
        protected boolean status;
        protected String message;

        public ResponseDB(boolean status, String message) {
            this.status = status;
            this.message = message;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    '}';
        }

}
