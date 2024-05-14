-- Create role table
create table role
(
    role_id     bigserial
        primary key,
    role_name   varchar(255),
    parent_role bigint
        references role
);

alter table role
    owner to root;

-- Create storage_object table
create table storage_object
(
    object_id     bigserial
        primary key,
    created_at    timestamp default CURRENT_TIMESTAMP not null,
    latested_at   timestamp,
    modified_at   timestamp,
    is_folder     boolean,
    object_format varchar(255),
    object_name   varchar(255),
    object_path   varchar(255),
    object_size   numeric(19, 2)
);

alter table storage_object
    owner to root;

-- Create user table
create table "user"
(
    user_id            bigserial
        primary key,
    user_phone_num     varchar(255)
        unique,
    user_password      varchar(255),
    role_id            bigint
        references role,
    user_password_hash varchar(255),
    useremail          varchar(255),
    status             varchar(255),
    clazz              varchar(255),
    created_at         timestamp default CURRENT_TIMESTAMP not null,
    modified_at        timestamp,
    latested_at        timestamp
);

alter table "user"
    owner to root;

-- Create todo table
create table todo
(
    to_do_id             bigserial
        primary key,
    to_do_title          varchar(255),
    to_do_content        varchar(255),
    request_answer_value varchar(255),
    target_day           timestamp,
    finished_day         timestamp,
    user_id              bigint
        references "user",
    request_user_id      bigint,
    solved_user_id       bigint,
    created_at           timestamp default CURRENT_TIMESTAMP not null,
    modified_at          timestamp,
    latested_at          timestamp
);

alter table todo
    owner to root;

-- Create wikipage table
create table wikipage
(
    page_id             bigserial
        primary key,
    page_title          varchar(255),
    page_content        text,
    page_category       varchar(255),
    page_path           varchar(255),
    object_id           bigint
        references storage_object,
    user_id             bigint
        references "user",
    created_at_user_id  bigint,
    modified_at_user_id bigint,
    to_do_id            bigint
        references todo,
    created_at          timestamp default CURRENT_TIMESTAMP not null,
    modified_at         timestamp,
    latested_at         timestamp
);

alter table wikipage
    owner to root;

-- Create browser_list table
create table browser_list
(
    browser_list_id bigserial
        primary key,
    page_id         bigint
        references wikipage,
    object_id       bigint
        references storage_object,
    user_id         bigint
        references "user",
    to_do_id        bigint
        references todo,
    created_at      timestamp default CURRENT_TIMESTAMP not null,
    modified_at     timestamp,
    latested_at     timestamp
);

alter table browser_list
    owner to root;

-- Create ancestor table
create table ancestor
(
    ancestor_id      bigint not null
        primary key,
    ancestor         varchar(255),
    parent_folder    varchar(255),
    parent_folder_id bigint
        references storage_object
);

alter table ancestor
    owner to root;
