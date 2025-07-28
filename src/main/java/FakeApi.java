import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FakeApi {

    private static final List<String> names = List.of(
            "Surya", "Kanni", "Phanten", "Kumaresh", "Kishore", "Magesh"
    );
    private static final Random random = new Random();

    // Slow source — no pressure needed
    public static Flux<String> getNameApi() {
        return Flux.fromIterable(names)
                .delayElements(Duration.ofMillis(500));
    }

    // Fast infinite source with backpressure strategy

    public static Flux<String> getFastNameApiWithBackpressure() {
        return Flux.interval(Duration.ofMillis(5)) 
                .map(i -> "Name-" + i)
                .map(String::valueOf)
                .take(100); 
    }
}
