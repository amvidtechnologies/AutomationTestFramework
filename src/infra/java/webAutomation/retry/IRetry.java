package webAutomation.retry;

public interface IRetry {
    void onAttempt() throws  Throwable;
    void onAttemptFail() throws Exception;
}
