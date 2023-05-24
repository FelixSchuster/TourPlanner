package at.fhtw.client.events;

import org.springframework.context.ApplicationEvent;

public class ApplicationErrorEvent extends ApplicationEvent {
    private String message;
    private Throwable throwable;

    public ApplicationErrorEvent(Object source, String message, Throwable throwable) {
        super(source);

        this.message = message;
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
