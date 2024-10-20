-- tasksテーブルからimage_url1, image_url2カラムを削除し、新しくimagesテーブルを作成する
drop table images;
create table images
(
    task_id   integer not null references tasks (id),
    image_url text    not null,
    primary key (task_id, image_url)
);

insert into images (task_id, image_url)
select id, image_url1
from tasks
where image_url1 is not null;

alter table tasks
    drop column image_url1;
alter table tasks
    drop column image_url2;
