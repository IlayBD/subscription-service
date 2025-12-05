package by.vasiliev.exception;

// ✅ Наследуем от RuntimeException — стандарт для бизнес-ошибок в Spring
public class CrmValidationException extends RuntimeException {

    // 🔑 Главное: этот конструктор ОБЯЗАТЕЛЕН
    public CrmValidationException(String message) {
        super(message);  // передаём сообщение родительскому классу (Throwable)
    }

    // Опционально: можно добавить конструктор с cause, но не обязательно
    public CrmValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}