import reactor.core.publisher.Flux;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Try different values: 1 (success), 2 (empty), 99 (error)
        Flux<String> api = FakeApi.getNameApi(1)
                .doOnSubscribe(s -> System.out.println("About to subscribe"))
                .doOnNext(s -> System.out.println("Processing: " + s))
                .doOnComplete(() -> System.out.println("Finished"))
                .doOnError(err -> System.out.println("doOnError: " + err.getMessage()))
                .doFinally(signal -> System.out.println("Signal: " + signal));

        api.subscribe(
                val -> System.out.println("Received: " + val),                      // onNext
                err -> System.out.println("Error handled: " + err.getMessage()),    // onError
                () -> System.out.println("Completed!")                              // onComplete
        );

        System.out.println("Main thread continues...");
        custom();
        Thread.sleep(4000);
    }
    public static void custom(){
        FakeApi.getNameApi(1).subscribe(new CustomSubscribe());
    }
}
