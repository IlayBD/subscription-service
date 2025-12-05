package by.vasiliev.exception;

// ✅ Наследуем от RuntimeException — стандарт для "не найдено"
public class ResourceNotFoundException extends RuntimeException {

    // 🔑 Обязательный конструктор с сообщением
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Опционально — с причиной (например, для логгирования стека)
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}