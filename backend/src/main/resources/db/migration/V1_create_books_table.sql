CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
);
