package dev.wantedpreonboardingbackend.recruitment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecruitment is a Querydsl query type for Recruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitment extends EntityPathBase<Recruitment> {

    private static final long serialVersionUID = -94875052L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecruitment recruitment = new QRecruitment("recruitment");

    public final dev.wantedpreonboardingbackend.company.domain.QCompany company;

    public final NumberPath<Long> compensation = createNumber("compensation", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath position = createString("position");

    public final StringPath requiredTech = createString("requiredTech");

    public QRecruitment(String variable) {
        this(Recruitment.class, forVariable(variable), INITS);
    }

    public QRecruitment(Path<? extends Recruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecruitment(PathMetadata metadata, PathInits inits) {
        this(Recruitment.class, metadata, inits);
    }

    public QRecruitment(Class<? extends Recruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new dev.wantedpreonboardingbackend.company.domain.QCompany(forProperty("company"), inits.get("company")) : null;
    }

}

