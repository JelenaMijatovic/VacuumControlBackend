package raf.jmijatovic11421rn.RAFVacuumControl.repositories;

import org.springframework.data.jpa.domain.Specification;
import raf.jmijatovic11421rn.RAFVacuumControl.model.Vacuum;

import java.util.Date;
import java.util.List;

public class VacuumSpec {
    public static Specification<Vacuum> vacuumNameLike(String name) {
        return ((root, query, criteriaBuilder) ->
            name == null ?
                    criteriaBuilder.conjunction() :
                    criteriaBuilder.like(root.get("vacuum").get("name"), "*" + name + "*"));
    }

    public static Specification<Vacuum> vacuumStatusIn(List<String> status) {
        return ((root, query, criteriaBuilder) ->
                status == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.in(root.get("vacuum").get("status").in(status)));
    }

    public static Specification<Vacuum> vacuumAddedByEquals(String addedBy) {
        return ((root, query, criteriaBuilder) ->
                addedBy == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("vacuum").get("added_by"), addedBy));
    }

    public static Specification<Vacuum> vacuumCreationDateBetween(Date dateFrom, Date dateTo) {
        return ((root, query, criteriaBuilder) ->
                dateFrom == null || dateTo == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.between(root.get("vacuum").get("creation_date"), dateFrom, dateTo));
    }

    public static Specification<Vacuum> vacuumActiveEquals(Boolean active) {
        return ((root, query, criteriaBuilder) ->
                active == null ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.isTrue(root.get("vacuum").get("active")));
    }
}
