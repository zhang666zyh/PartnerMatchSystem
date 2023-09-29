# 项目搭建

## web

```shell
yarn create vite
```

- 首次报错, 这是我自己本地弄了多版本 node, 只有一个的话大概率不会出这个问题
- 执行

```shell
npm config get cache
yarn config set cache-folder "上一条指令的结果"
npm config get prefix
yarn config set global-folder "上一条指令的结果"
```

- 然后

```shell
F:\Code\WriteCodes\Vue\PartnerMatchSystem>yarn create vite
yarn create v1.22.19
[1/4] Resolving packages...
[2/4] Fetching packages...
[3/4] Linking dependencies...
[4/4] Building fresh packages...
success Installed "create-vite@4.4.1" with binaries:
      - create-vite
      - cva
√ Project name: ... web
√ Select a framework: » Vue
√ Select a variant: » TypeScript
```

```shell
cd web
npm install
```

```shell
npm i vite-plugin-style-import@1.4.1 -D
npm i vant
```

`/web/vite.config.ts`

```typescript
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import styleImport, { VantResolve } from 'vite-plugin-style-import'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    styleImport({
      resolves: [VantResolve()],
      libs: [
        {
          libraryName: 'vant',
          esModule: true,
          resolveStyle: (name) => `../es/${name}/style`,
        },
      ],
    }),
  ],
})
```

`/web/src/main.ts`

```typescript
import { createApp } from 'vue'
import App from './App.vue'
import { Button } from 'vant'

const app = createApp(App)
app.use(Button)

app.mount('#app')
```

## server

```sql
create database if not exists partner_manage_system;

use partner_manage_system;
create table if not exists user(
    username     varchar(256)                       null comment '用户昵称',
    id           bigint auto_increment comment 'id'
    primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号',
    tags         varchar(1024)                      not null comment '标签'
) comment '用户';

create table if not exists tag
(
    id         bigint auto_increment comment 'id'
        primary key,
    tagName    varchar(256)                       null comment '标签名称',
    userId     bigint                             null comment '用户id',
    parentId   bigint                             null comment '父标签id',
    isParent   tinyint                            null comment '0 - 不是, 1 - 父标签',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete   tinyint  default 0                 not null comment '是否删除',
    constraint uniIdx_tagName
        unique (tagName)
) comment '标签';

create index idx_userId on tag (userId);
```

---
