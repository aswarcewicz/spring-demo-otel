package com.example.demootel;

import io.micrometer.context.ContextRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.contextpropagation.ObservationAwareBaggageThreadLocalAccessor;
import io.micrometer.tracing.contextpropagation.ObservationAwareSpanThreadLocalAccessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservabilityConfiguration {
    ObservabilityConfiguration(Tracer tracer, ObservationRegistry registry) {
        ContextRegistry.getInstance().registerThreadLocalAccessor(new ObservationAwareSpanThreadLocalAccessor(tracer));
        ContextRegistry.getInstance().registerThreadLocalAccessor(new ObservationAwareBaggageThreadLocalAccessor(registry, tracer));
    }
}
