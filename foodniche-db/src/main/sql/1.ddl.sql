DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;


create sequence Sequence_CouponID;

create sequence Sequence_DownloadID;

create sequence Sequence_FoodPreferenceID;

create sequence Sequence_GroupID;

create sequence Sequence_IngredientID;

create sequence Sequence_MessageID;

create sequence Sequence_PaymentID;

create sequence Sequence_RecipeID;

create sequence Sequence_SubscriptionID;

create sequence Sequence_UserID;

/*==============================================================*/
/* Domain: Domain_1                                             */
/*==============================================================*/
create domain Domain_1 as CHAR(10);

/*==============================================================*/
/* Table: BusinessConnections                                   */
/*==============================================================*/
create table BusinessConnections (
   BusinessID           INT4                 null,
   UserID               INT4                 null
);

comment on table BusinessConnections is
'Users who follow this company';

/*==============================================================*/
/* Table: BusinessEmployees                                     */
/*==============================================================*/
create table BusinessEmployees (
   UserID               INT4                 null,
   BusinessID           INT4                 null
);

comment on table BusinessEmployees is
'Users who works for this company';

/*==============================================================*/
/* Table: BusinessTypes                                         */
/*==============================================================*/
create table BusinessTypes (
   BusinessTypeID       INT4                 not null,
   Name                 VARCHAR(80)          null,
   constraint PK_BUSINESSTYPES primary key (BusinessTypeID)
);

comment on table BusinessTypes is
'Table contain types of business: f.e.  fast casual restaurant, deli restaurant, food producer, etc..';

/*==============================================================*/
/* Table: Businesses                                            */
/*==============================================================*/
create table Businesses (
   BusinessID           SERIAL not null,
   BusinessTypeID       INT4                 null,
   UserID               INT4                 not null,
   Name                 VARCHAR(80)          null,
   ZipCode              VARCHAR(80)          null,
   WebSite              VARCHAR(255)         null,
   Description          TEXT                 null,
   constraint PK_BUSINESSES primary key (BusinessID)
);

/*==============================================================*/
/* Table: Connections                                           */
/*==============================================================*/
create table Connections (
   FromUserID           INT4                 not null,
   ToUserID             INT4                 not null,
   Status               INT2                 null,
   CreatedDate          TIMESTAMP            null,
   ApprovedDate         TIMESTAMP            null,
   DeletedDate          TIMESTAMP            null,
   constraint PK_CONNECTIONS primary key (FromUserID, ToUserID)
);

comment on table Connections is
'Table contains info about connections between users';

comment on column Connections.Status is
'For Example:
0 - Connection requested
1 - Connection accepted
2 - Connection deleted';

/*==============================================================*/
/* Table: Content                                               */
/*==============================================================*/
create table Content (
   ContentID            SERIAL               not null,
   ContentTypeID        INT4                 null,
   Name                 VARCHAR(255)         null,
   UserID               INT4                 null,
   BusinessID           INT4                 null,
   GroupID              INT4                 null,
   ProductID            INT4                 null,
   ContentDate          TIMESTAMP            null,
   Content              TEXT                 null,
   FileName             VARCHAR(255)         null,
   constraint PK_CONTENT primary key (ContentID)
);

comment on table Content is
'Contains all content published on website.
Always has UserID - ID of user who published it
May contain links GroupID and CompanyID if content was published for these entities';

comment on column Content.Name is
'Short name of content';

comment on column Content.UserID is
'User who published this content';

comment on column Content.BusinessID is
'If this content is about this company (review, menu item etc) this field is set. 
Otherwise = NULL';

comment on column Content.GroupID is
'If this content shoud be published for the group - this field is set.
Otherwie = NULL';

comment on column Content.Content is
'Content itself';

comment on column Content.FileName is
'Filename on the server of video/picture file';

/*==============================================================*/
/* Table: ContentTypes                                          */
/*==============================================================*/
create table ContentTypes (
   ContentTypeID        INT4                 not null,
   Name                 VARCHAR(80)          null,
   constraint PK_CONTENTTYPES primary key (ContentTypeID)
);

comment on table ContentTypes is
'Contains rows for each content type published on the website
It can be:
- Reviews
- Photo
- Video
- Tutorial
- Menu Items
- ...';

/*==============================================================*/
/* Table: CouponDownloads                                       */
/*==============================================================*/
create table CouponDownloads (
   DownloadID           SERIAL not null,
   CouponID             INT4                 null,
   UserID               INT4                 null,
   Amount               INT4                 null,
   DownloadDate         TIMESTAMP            null,
   constraint PK_COUPONDOWNLOADS primary key (DownloadID)
);

comment on table CouponDownloads is
'Info about Users who download coupons';

comment on column CouponDownloads.Amount is
'Number of dowloaded coupons';

/*==============================================================*/
/* Table: CouponTypes                                           */
/*==============================================================*/
create table CouponTypes (
   CouponTypeID         INT4                 not null,
   Description          VARCHAR(255)         null,
   constraint PK_COUPONTYPES primary key (CouponTypeID)
);

/*==============================================================*/
/* Table: Coupons                                               */
/*==============================================================*/
create table Coupons (
   CouponID             SERIAL not null,
   BusinessID           INT4                 null,
   CouponTypeID         INT4                 null,
   Name                 VARCHAR(80)          null,
   Description          TEXT                 null,
   ValidFrom            DATE                 null,
   ValidTo              DATE                 null,
   constraint PK_COUPONS primary key (CouponID)
);

comment on table Coupons is
'Coupons info';

comment on column Coupons.Name is
'Short name of coupon';

/*==============================================================*/
/* Table: FoodPreferences                                       */
/*==============================================================*/
create table FoodPreferences (
   FoodPreferenceID     SERIAL not null,
   Name                 VARCHAR(80)          null,
   constraint PK_FOODPREFERENCES primary key (FoodPreferenceID)
);

comment on table FoodPreferences is
'Types of food preferences. For example:
vegetarian_food, Kosher_food, other_food, gluten-free_food, 
Cuisine – Ethnic_cuisine, American_cuisine, steakhouse_cuisine, seafood_cuisine, ';

/*==============================================================*/
/* Table: GroupAdmins                                           */
/*==============================================================*/
create table GroupAdmins (
   GroupID              INT4                 null,
   UserID               INT4                 null
);

comment on table GroupAdmins is
'List of Users who can approve group membership';

/*==============================================================*/
/* Table: Groups                                                */
/*==============================================================*/
create table Groups (
   GroupID              SERIAL not null,
   Name                 VARCHAR(80)          null,
   Theme                VARCHAR(255)         null,
   Description          VARCHAR(255)         null,
   ApprovedMembership   BOOL                 null,
   constraint PK_GROUPS primary key (GroupID)
);

comment on table Groups is
'Groups of Users';

comment on column Groups.ApprovedMembership is
'0 - (FALSE) Do not need to approve membership in the group
1 - (TRUE) Membership in the group should be approved by GroupAdmins';

/*==============================================================*/
/* Table: Ingredients                                           */
/*==============================================================*/
create table Ingredients (
   IngredientID         SERIAL not null,
   Name                 VARCHAR(80)          null,
   Picture              VARCHAR(255)         null,
   constraint PK_INGREDIENTS primary key (IngredientID)
);

comment on table Ingredients is
'Contains list of possible ingredients';

comment on column Ingredients.Picture is
'File names of image files';


/*==============================================================*/
/* Table: Logins                                                */
/*==============================================================*/
create table Logins (
   LoginType            INT2                 null,
   LoginKey             VARCHAR(80)          null,
   UserID               INT4                 null
);

comment on table Logins is
'Contains records for external serices logins like Facebook, Twitter etc';

comment on column Logins.LoginType is
'1 - Facebook
2 - Twitter
3 - FoodNich';

comment on column Logins.LoginKey is
'Autorization Key for external service';

/*==============================================================*/
/* Table: Messages                                              */
/*==============================================================*/
create table Messages (
   MessageID            SERIAL not null,
   MessageDate          TIMESTAMP            null,
   FromUserID           INT4                 null,
   ToUserID             INT4                 null,
   Content              TEXT                 null,
   Status               INT2                 null,
   constraint PK_MESSAGES primary key (MessageID)
);

comment on table Messages is
'Messages between Users';

comment on column Messages.Status is
'0 - Unread
1 - Read';

/*==============================================================*/
/* Table: Payments                                              */
/*==============================================================*/
create table Payments (
   PaymentID            SERIAL not null,
   UserID               INT4                 null,
   SubscriptionID       INT4                 null,
   PaymentDateTime      TIMESTAMP            null,
   Amount               MONEY                null,
   Source               VARCHAR(80)          null,
   constraint PK_PAYMENTS primary key (PaymentID)
);

comment on table Payments is
'Payment from the user for business premium/gold accounts';

/*==============================================================*/
/* Table: RecipeIngredients                                     */
/*==============================================================*/
create table RecipeIngredients (
   RecipeID             INT4                 null,
   IngredientID         INT4                 null
);

comment on table RecipeIngredients is
'Contains ingredients of particular recipe';

/*==============================================================*/
/* Table: Recipes                                               */
/*==============================================================*/
create table Recipes (
   RecipeID             SERIAL not null,
   UserID               INT4                 null,
   Description          TEXT                 null,
   TimeToPrepare        INT4                 null,
   RecipeDate           TIMESTAMP            null,
   Picture              VARCHAR(255)         null,
   Video                VARCHAR(255)         null,
   constraint PK_RECIPES primary key (RecipeID)
);

comment on table Recipes is
'Contains recipes ';

comment on column Recipes.Picture is
'File names of image files';

comment on column Recipes.Video is
'File names of video files';

/*==============================================================*/
/* Table: SubcsriptionTypes                                     */
/*==============================================================*/
create table SubcsriptionTypes (
   SubscriptionTypeID   INT4                 not null,
   Name                 VARCHAR(80)          null,
   constraint PK_SUBCSRIPTIONTYPES primary key (SubscriptionTypeID)
);

comment on table SubcsriptionTypes is
'Contains different types of subscription

0- Business Basic
1- Business Siver
2 - Business Gold
3- Business Premium
4 - Individual Basic
5 - Individual Premium';

/*==============================================================*/
/* Table: Subscriptions                                         */
/*==============================================================*/
create table Subscriptions (
   SubscriptionID       SERIAL not null,
   BusinessID           INT4                 null,
   UserID               INT4                 null,
   SubscriptionTypeID   INT4                 null,
   ValidFrom            DATE                 null,
   ValidTo              DATE                 null,
   constraint PK_SUBSCRIPTIONS primary key (SubscriptionID)
);

/*==============================================================*/
/* Table: UserFoodPreferences                                   */
/*==============================================================*/
create table UserFoodPreferences (
   UserID               INT4                 not null,
   FoodPreferenceID     INT4                 not null,
   Description          TEXT                 null,
   constraint PK_USERFOODPREFERENCES primary key (UserID, FoodPreferenceID)
);

comment on table UserFoodPreferences is
'Food prefrence for particular user';

/*==============================================================*/
/* Table: UserGroups                                            */
/*==============================================================*/
create table UserGroups (
   GroupID              INT4                 null,
   UserID               INT4                 null,
   Status               CHAR(10)             null
);

comment on table UserGroups is
'Membership of users in groups';

comment on column UserGroups.Status is
'0 - Waiting for approval
1 - Approved
2 - Declined';

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users (
   UserID               SERIAL not null,
   UserName             VARCHAR(80)          null,
   Password             VARCHAR(80)          null,
   FirstName            VARCHAR(80)          null,
   LastName             VARCHAR(80)          null,
   email                VARCHAR(100)         null,
   ZipCode              VARCHAR(80)          null,
   Role                 VARCHAR(80)          not null,
   Status               INT2                 null,
   ProfilePicture       VARCHAR(255)         null,
   AccountType          INT2                 null,
   constraint PK_USERS primary key (UserID)
);

/*==============================================================*/
/* Table: UploadedFiles                                         */
/*==============================================================*/
create table UploadedFiles (
   FileID               SERIAL               not null,
   ServerFileName       VARCHAR(255)         not null,
   FileName             VARCHAR(255)         not null,
   сontentType          VARCHAR(80)          not null,
   FileType             VARCHAR(80)          not null,
   FileSize             bigint               not null,
   UserID               INT4                 not null,
   constraint PK_UPLOADED_FILES primary key (FileID)
);

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product (
   ProductID            SERIAL               not null,
   price                INT4                 not null,
   likes                INT4                 not null,
   photoUrl             VARCHAR(100)         not null,
   description          VARCHAR(500)         not null,
   BusinessID           INT4                 not null,
   constraint PK_PRODUCT primary key (ProductID)
);

comment on table Users is
'Users table';

comment on column Users.UserName is
'Should be unique too (create unique index)';

comment on column Users.Status is
'0 - Active
1 - Deleted ';

comment on column Users.ProfilePicture is
'Picture file name on the server';

comment on column Users.AccountType is
'0 - Individual
1 - Business';

alter table BusinessConnections
   add constraint FK_BUSINESS_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;

alter table BusinessConnections
   add constraint FK_BUSINESS_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table BusinessEmployees
   add constraint FK_BUSINESS_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table BusinessEmployees
   add constraint FK_BUSINESS_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;

alter table Businesses
   add constraint FK_BUSINESS_REFERENCE_BUSINESS foreign key (BusinessTypeID)
      references BusinessTypes (BusinessTypeID)
      on delete restrict on update restrict;

alter table Businesses
   add constraint FK_USER_BUSINESS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Connections
   add constraint FK_CONNECTI_CONNECT_USERS foreign key (FromUserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Connections
   add constraint FK_CONNECTI_REFERENCE_USERS foreign key (ToUserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Content
   add constraint FK_CONTENT_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Content
   add constraint FK_CONTENT_REFERENCE_GROUPS foreign key (GroupID)
      references Groups (GroupID)
      on delete restrict on update restrict;

alter table Content
   add constraint FK_CONTENT_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;

alter table Content
   add constraint FK_CONTENT_REFERENCE_PRODUCT foreign key (ProductID)
      references Product (ProductID)
      on delete restrict on update restrict;

alter table Content
   add constraint FK_CONTENT_REFERENCE_CONTENTT foreign key (ContentTypeID)
      references ContentTypes (ContentTypeID)
      on delete restrict on update restrict;

alter table CouponDownloads
   add constraint FK_COUPONDO_REFERENCE_COUPONS foreign key (CouponID)
      references Coupons (CouponID)
      on delete restrict on update restrict;

alter table CouponDownloads
   add constraint FK_COUPONDO_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Coupons
   add constraint FK_COUPONS_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;

alter table Coupons
   add constraint FK_COUPONS_REFERENCE_COUPONTY foreign key (CouponTypeID)
      references CouponTypes (CouponTypeID)
      on delete restrict on update restrict;

alter table GroupAdmins
   add constraint FK_GROUPADM_REFERENCE_GROUPS foreign key (GroupID)
      references Groups (GroupID)
      on delete restrict on update restrict;

alter table GroupAdmins
   add constraint FK_GROUPADM_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Logins
   add constraint FK_LOGINS_LOGINUSER_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Messages
   add constraint FK_MESSAGES_MESSAGE_USERS foreign key (FromUserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Messages
   add constraint FK_MESSAGES_REFERENCE_USERS foreign key (ToUserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Payments
   add constraint FK_PAYMENTS_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Payments
   add constraint FK_PAYMENTS_REFERENCE_SUBSCRIP foreign key (SubscriptionID)
      references Subscriptions (SubscriptionID)
      on delete restrict on update restrict;

alter table RecipeIngredients
   add constraint FK_RECIPEIN_REFERENCE_RECIPES foreign key (RecipeID)
      references Recipes (RecipeID)
      on delete restrict on update restrict;

alter table RecipeIngredients
   add constraint FK_RECIPEIN_REFERENCE_INGREDIE foreign key (IngredientID)
      references Ingredients (IngredientID)
      on delete restrict on update restrict;

alter table Recipes
   add constraint FK_RECIPES_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Subscriptions
   add constraint FK_SUBSCRIP_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;

alter table Subscriptions
   add constraint FK_SUBSCRIP_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Subscriptions
   add constraint FK_SUBSCRIP_REFERENCE_SUBCSRIP foreign key (SubscriptionTypeID)
      references SubcsriptionTypes (SubscriptionTypeID)
      on delete restrict on update restrict;

alter table UserFoodPreferences
   add constraint FK_USERFOOD_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table UserFoodPreferences
   add constraint FK_USERFOOD_REFERENCE_FOODPREF foreign key (FoodPreferenceID)
      references FoodPreferences (FoodPreferenceID)
      on delete restrict on update restrict;

alter table UserGroups
   add constraint FK_USERGROU_REFERENCE_GROUPS foreign key (GroupID)
      references Groups (GroupID)
      on delete restrict on update restrict;

alter table UserGroups
   add constraint FK_USERGROU_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table UploadedFiles
   add constraint FK_UPLOADS_REFERENCE_USERS foreign key (UserID)
      references Users (UserID)
      on delete restrict on update restrict;

alter table Product
   add constraint FK_PRODUCT_REFERENCE_BUSINESS foreign key (BusinessID)
      references Businesses (BusinessID)
      on delete restrict on update restrict;