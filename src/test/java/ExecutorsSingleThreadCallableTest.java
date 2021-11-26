import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ExecutorsSingleThreadCallableTest {

    @Test
    public void testExecutorsOneTaskWithAwaitTerminationInFiveSeconds() throws InterruptedException {

        ExecutorsSingleThreadCallable.main(new String[]{"5", ChronoUnit.SECONDS.name(), "testExecutorsOneTaskWithAwaitTerminationInFiveSeconds"});

        long startTime = ExecutorsSingleThreadCallable.startTime;
        long endTime = ExecutorsSingleThreadCallable.endTime;
        Duration duration = Duration.ofNanos(endTime - startTime);
        long resultTime = duration.toSeconds();

        assertNotEquals(resultTime, 0L);
        assertEquals(resultTime, 5L);
    }
}
