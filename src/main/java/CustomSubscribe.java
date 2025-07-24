import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public class CustomSubscribe implements Subscriber {


    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Inside onSubscribe");
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object item) {
        System.out.println("Inside onNext");
        System.out.println(item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Inside onError");
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
       System.out.println("Completed");
    }
}
