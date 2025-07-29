import reactor.core.publisher.Flux;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        Flux<String> api = FakeApi.getNameApi()
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(name->{
                    if(name.equals("Kishore")) throw new RuntimeException("Error Occurred");
                    return name.toUpperCase();
                })
                .doOnSubscribe(s -> System.out.println("About to subscribe"))
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

        FakeApi.getFastNameApiWithBackpressure().onBackpressureBuffer().delayElements(Duration.ofMillis(50)).subscribe(System.out::println);

        Thread.sleep(5000);
    }
    public static void custom(){
        FakeApi.getNameApi().subscribe(new CustomSubscribe());
    }
}
