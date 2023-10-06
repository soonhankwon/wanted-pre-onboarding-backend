package dev.wantedpreonboardingbackend.recruitment.service;

import dev.wantedpreonboardingbackend.recruitment.domain.Recruitment;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class RequirementSpecifications {
    public static Specification<Recruitment> searchByKeyword(String keyword) {
        return (Root<Recruitment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No filtering applied
            } else {
                String likePattern = "%" + keyword + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("requiredTech"), likePattern),
                        criteriaBuilder.like(root.get("position"), likePattern),
                        criteriaBuilder.like(root.get("description"), likePattern),
                        criteriaBuilder.like(root.get("company").get("name"), likePattern)
                );
            }
        };
    }
}
