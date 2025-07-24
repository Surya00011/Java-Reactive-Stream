import reactor.core.publisher.Flux;
import java.time.Duration;

public class FakeApi {
    public static Flux<String> getNameApi() {
        return
            Flux.just("Surya", "Kanni", "Phanten", "Kumaresh", "Kishore", "Magesh")
                    .delayElements(Duration.ofMillis(500));
    }
}
