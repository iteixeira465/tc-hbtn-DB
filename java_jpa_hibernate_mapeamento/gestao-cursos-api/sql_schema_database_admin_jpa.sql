-- TABLE
CREATE TABLE tb_aluno (id  integer, email varchar(255), numero_matricula varchar(255), data_nascimento date, nome_completo varchar(255), primary key (id));
CREATE TABLE tb_aluno_curso (curso_id bigint not null, aluno_id bigint not null);
CREATE TABLE tb_curso (id  integer, nome varchar(255), sigla varchar(255), material_curso_id bigint, professor_id bigint, primary key (id));
CREATE TABLE tb_endereco (id  integer, bairro varchar(255), cep varchar(255), cidade varchar(255), endereco varchar(255), estado varchar(255), logradouro varchar(255), numero varchar(255), aluno_id bigint, primary key (id));
CREATE TABLE tb_material_curso (id  integer, url varchar(255), primary key (id));
CREATE TABLE tb_professor (id  integer, email varchar(255), numero_matricula varchar(255), nomeCompleto varchar(255), primary key (id));
CREATE TABLE tb_telefone (id  integer, ddd varchar(255), numero_telefone varchar(255), aluno_id bigint, primary key (id));
 
-- INDEX
 
-- TRIGGER
 
-- VIEW
 
