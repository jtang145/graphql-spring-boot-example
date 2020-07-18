package cn.naches.examples.graphql.pets.resolver;

import cn.naches.examples.graphql.pets.entity.Pet;
import cn.naches.examples.graphql.pets.service.PetService;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class Subscription implements GraphQLSubscriptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(Subscription.class);

    @Autowired
    PetService petService;

    private final BlockingQueue<Future<Pet>> newPetQueue = new LinkedBlockingQueue<>(100);
    private Flowable<Pet> newPetPublisher;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        petService.initNewPetQueue(newPetQueue);
        initPublishers();
    }

    private void initPublishers() {
        initNewPetPublisher();
    }

    private void initNewPetPublisher() {
        Observable<Pet> observable = Observable.create(e -> {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                logger.info("new schedule for newPet subscription");
                while (true) {
                    try {
                        Pet pet = newPetQueue.take().get();
                        logger.info("new Pet subscription arrived for " + pet.getName());
                        e.onNext(pet);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        });

        ConnectableObservable connectableObservable = observable.share().publish();
        connectableObservable.connect();
        this.newPetPublisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public Publisher<Pet> newPet() {
        logger.info("newPet subscribed.");
        return this.newPetPublisher;
    }
}
