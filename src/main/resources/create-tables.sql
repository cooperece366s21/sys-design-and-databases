CREATE TABLE IF NOT EXISTS users
(
    id   integer PRIMARY KEY,
    name text not null
);

CREATE TABLE IF NOT EXISTS posts
(
    id        integer PRIMARY KEY,
    content   text not null,
    blob_url  text not null,
    author_id integer,
    FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id        integer PRIMARY KEY,
    content   text not null,
    post_id   integer,
    author_id integer,
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS upvotes
(
    post_id integer,
    user_id integer,
    foreign key (post_id) references posts,
    foreign key (user_id) references users,
    primary key (post_id, user_id)
);

