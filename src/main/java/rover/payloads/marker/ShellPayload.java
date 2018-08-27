package rover.payloads.marker;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ShellPayload {

    Class<?> reply() default void.class;

    Class<?> request() default void.class;
}
