package model.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MulThreadSharedData {
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.CLASS.SOURCE)
    public @interface MulThreadShareData{}
}
