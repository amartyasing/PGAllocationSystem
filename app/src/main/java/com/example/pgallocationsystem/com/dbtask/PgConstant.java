package com.example.pgallocationsystem.com.dbtask;

public class PgConstant {
    public static final String DB_NAME="PgAllocationDb";
    public static final int DB_VERSION=1;
    public static final String FIRST_TABLE_NAME="RoomType";
    public static final String TYP_ID="Typeid";
    public static final String TYP_NAME="Typename";
    public static final String SECOND_TABLE_NAME="RoomCategory";
    public static final String ROOM_ID="Roomid";
    public static final String ROOMCATEGORY_TYP_ID="Typeid";
    public static final String NOOFROOMS="Noofrooms";
    public static final String EQUIPPED_WITH="Equippedwith";
    public static final String MONTHLY_CHARGE="Monthlycharge";
    public static final String FACILITIES="Facilities";
    public static final String THIRD_TABLE_NAME="RoomDetails";
    public static final String ROOM_NO="Roomno";
    public static final String ROOMDETAILS_ROOM_ID="Roomid";
    public static final String STATUS="Status";
    public static final String NOOFCANDIDATE="Noofcandidate";
    public static final String FOURTH_TABLE_NAME="RoomAllotment";
    public static final String ALLOT_ID="Allotid";
    public static final String CID="Cid";
    public static final String ROOMALLOTMENT_ROOM_ID="Roomid";
    public static final String ROOMALLOTMENT_ROOM_NO="Roomno";
    public static final String DATEOFALLOTMENT="Dateofallotment";
    public static final String FIFH_TABLE_NAME="CandidateDetails";
    public static final String CANDIDATEDETAILS_CID="Cid";
    public static final String NAME="Name";
    public static final String EMAIL="Email";
    public static final String PHONE_NO="Phoneno";
    public static final String ADDRESS="Address";
    public static final String GENDER="Gender";
    public static final String AGE="Age";
    public static final String PROFESSION="Profession";
    public static final String COLLEGE_NAME="Collegename";
    public static final String COMPANY_NAME="Companyname";
    public static final String ALTERNATE_PHONE_NO="Alternatephoneno";

    public static final String TBL_QUERY_ROOMTYPE="create table RoomType(Typeid text primary key,Typename text not null)";
    public static final String TBL_QUERY_ROOMCATEGORY="create table RoomCategory(Roomid text primary key,Typeid text not null,Noofrooms integer not null,Equippedwith text not null,Monthlycharge integer not null,Facilities text not null)";

    public static final String TBL_QUERY_ROOMDETAILS="create table RoomDetails(Roomno integer primary key,Roomid text,Status text,Noofcandidate integer,Foreign Key (Roomid) references RoomCategory(Roomid))";

    public static final String TBL_QUERY_ROOMALLOTMENT="create table RoomAllotment(Allotid text primary key,Cid text not null,Roomid text not null,Roomno integer not null,Dateofallotment date not null)";
    public static final String TBL_QUERY_CANDIDATEDETAILS="create table CandidateDetails(Cid text primary key,Name text not null,Email text not null,Phoneno text not null,Address text not null,Gender text not null,Age integer not null,Profession text not null,Collegename text,Companyname text,Alternatephoneno text not null)";


}
