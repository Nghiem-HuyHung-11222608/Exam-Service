CREATE TYPE exam_type AS ENUM (
    'ONLINE_EXAM',
    'OFFLINE_EXAM'
);

CREATE TYPE question_type AS ENUM (
    'SINGLE_CHOICE',
    'MULTIPLE_CHOICE'
);

CREATE TABLE exam (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    type exam_type NOT NULL,
    duration_minutes INTEGER,
    location VARCHAR(255),
    slot_time TIMESTAMP,
    lesson_id BIGINT
);

CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(1000) NOT NULL,
    type question_type NOT NULL
);

CREATE TABLE question_choice (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question(id) ON DELETE CASCADE,
    choice TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE exam_question (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question(id) ON DELETE CASCADE,
    exam_id BIGINT NOT NULL REFERENCES exam(id) ON DELETE CASCADE
);

CREATE TABLE exam_answer (
    id BIGSERIAL PRIMARY KEY,
    exam_id BIGINT NOT NULL REFERENCES exam(id) ON DELETE CASCADE,
    user_id VARCHAR(255) NOT NULL,
    score INTEGER,
    submitted_at TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (exam_id, user_id)
);

CREATE TABLE exam_answer_choice (
    id BIGSERIAL PRIMARY KEY,
    exam_answer_id BIGINT NOT NULL REFERENCES exam_answer(id) ON DELETE CASCADE,
    question_id BIGINT NOT NULL,
    question_choice_id BIGINT NOT NULL,
    UNIQUE (exam_answer_id, question_id, question_choice_id)
);
