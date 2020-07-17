package cn.naches.examples.graphql.pets.resolver;

import cn.naches.examples.graphql.pets.entity.Pet;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Subscription implements GraphQLSubscriptionResolver {

    public Publisher<Pet> data() {
        Observable<Pet> observable = Observable.create(e -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(() -> {
                Pet pet = new Pet();
//                pet.setMessage("hello " + new Date().toString());
                e.onNext(pet);
            }, 0, 2, TimeUnit.SECONDS);
        });

        ConnectableObservable connectableObservable = observable.share().publish();
        connectableObservable.connect();
        return connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }
}
