CREATE TABLE user_gyms (
    id         VARCHAR(50) NOT NULL,
    user_id    VARCHAR(50) NOT NULL,
    gym_id     VARCHAR(50) NOT NULL,
    joined_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT pk_user_gyms
        PRIMARY KEY (id),

    CONSTRAINT uk_user_gyms_user_gym
        UNIQUE (user_id, gym_id),

    CONSTRAINT fk_user_gyms_user
        FOREIGN KEY (user_id) REFERENCES users (id),

    CONSTRAINT fk_user_gyms_gym
        FOREIGN KEY (gym_id) REFERENCES gyms (id)
);

CREATE INDEX idx_user_gyms_gym_id ON user_gyms (gym_id);