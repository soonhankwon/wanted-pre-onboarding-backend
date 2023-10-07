package dev.wantedpreonboardingbackend.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRecruitment is a Querydsl query type for UserRecruitment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRecruitment extends EntityPathBase<UserRecruitment> {

    private static final long serialVersionUID = -766206566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRecruitment userRecruitment = new QUserRecruitment("userRecruitment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dev.wantedpreonboardingbackend.recruitment.domain.QRecruitment recruitment;

    public final StringPath status = createString("status");

    public final QUser user;

    public QUserRecruitment(String variable) {
        this(UserRecruitment.class, forVariable(variable), INITS);
    }

    public QUserRecruitment(Path<? extends UserRecruitment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserRecruitment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserRecruitment(PathMetadata metadata, PathInits inits) {
        this(UserRecruitment.class, metadata, inits);
    }

    public QUserRecruitment(Class<? extends UserRecruitment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recruitment = inits.isInitialized("recruitment") ? new dev.wantedpreonboardingbackend.recruitment.domain.QRecruitment(forProperty("recruitment"), inits.get("recruitment")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

