package com.duanwu.dwb.base.annotation;

import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.*;

/**
 * 匹配映射中的**参数
 * @see org.springframework.web.bind.annotation.RequestMapping
 * Created by RexQian on 2017/2/14.
 */
@SuppressWarnings("unused")
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MatchPathVariable {

    /**
     * The default value to use as a fallback when the request parameter is
     * not provided or has an empty value.
     * <p>Supplying a default value implicitly sets {@link #required} to
     * {@code false}.
     */
    String defaultValue() default ValueConstants.DEFAULT_NONE;

}
