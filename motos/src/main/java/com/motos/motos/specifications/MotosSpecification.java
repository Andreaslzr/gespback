package com.motos.motos.specifications;

import com.motos.motos.models.Motos;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class MotosSpecification {

    @And({
            @Spec(path = "frente.title", params = "frenteTitle", spec = LikeIgnoreCase.class),
            @Spec(path = "motor.title", params = "motorTitle", spec = LikeIgnoreCase.class),
            @Spec(path = "tanque.title", params = "tanqueTitle", spec = LikeIgnoreCase.class),
            @Spec(path = "tras.title", params = "trasTitle", spec = LikeIgnoreCase.class),

            @Spec(path = "frente.type", params = "frenteType", spec = EqualIgnoreCase.class),
            @Spec(path = "motor.type", params = "motorType", spec = EqualIgnoreCase.class),
            @Spec(path = "tanque.type", params = "tanqueType", spec = EqualIgnoreCase.class),
            @Spec(path = "tras.type", params = "trasType", spec = EqualIgnoreCase.class),

            @Spec(path = "frente.id", params = "frenteId", spec = EqualIgnoreCase.class),
            @Spec(path = "motor.id", params = "motorId", spec = EqualIgnoreCase.class),
            @Spec(path = "tanque.id", params = "tanqueId", spec = EqualIgnoreCase.class),
            @Spec(path = "tras.id", params = "trasId", spec = EqualIgnoreCase.class),

    })
    public interface MotosSpec extends Specification<Motos> {}
}
