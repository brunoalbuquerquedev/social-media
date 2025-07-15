package project.social.dto.config;

public record StandardErrorDto(
        String timestamp,
        Integer status,
        String error,
        String message,
        String path
) {

    public StandardErrorDto(String timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
