import reactor.core.publisher.Flux;
import java.time.Duration;

public class FakeApi {
    public static Flux<String> getNameApi(int num) {
        return switch (num) {
            case 1 -> Flux.just("Surya", "Kanni", "Dinesh", "Kumaresh", "Kishore", "Magesh")
                    .delayElements(Duration.ofMillis(500)); // slight delay
            case 2 -> Flux.empty(); // No items, completes immediately
            default -> Flux.error(new IllegalStateException("Custom err: " + num));
        };
    }
}
