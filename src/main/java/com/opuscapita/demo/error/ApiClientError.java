package com.opuscapita.demo.error;

public enum ApiClientError {
    OUTDATED_VERSION,
    OTHER_ERROR;

    public Error get() {
        return get(null);
    }
    public Error get(String details) {
        return new Error(this.name(), details);
    }

    public static class Error extends RuntimeException {
        private String details;

        public Error(String errorCode, String details) {
            super(errorCode);
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
}
