# Spring Redis And Cache 학습 레포지토리

## Cache
* 메인 메모리와 CPU 간의 데이터 속도 향상을 위한 중간 버퍼 역할을 하는 CPU 내 또는 외에 존재하는 메모리
* 실제 메모리와 CPU 사이에서 빠르게 전달을 위해서 미리 데이터들을 저장해두는 좀 더 빠른 메모리로 전체 시스템의 성능의 개선을 시킬 수 있다.
* 자주 사용하는 데이터나 값을 미리 복사해 놓는 기법
* 저장 공간이 작고 비용이 비싼 대신 빠른 성능을 제공
* 원본 데이터를 가져오는 시간이 오래 걸리는 경우(서버의 균일한 API 데이터) / 반복적으로 동일한 결과를 돌려주는 경우(이미지나 썸네일 등) 등에 사용한다.

![image](https://user-images.githubusercontent.com/77544214/157221096-5906a53c-dbcf-428f-a54f-ec0b52f4afbf.png)

* Cache에 데이터를 미리 복사해 놓으면 계산이나 접근 시간 없이 더 빠른 속도로 데이터에 접근할 수 있다. 
* Cache란 반복적으로 데이터를 불러오는 경우에, 지속적으로 DBMS 혹은 서버에 요청하는 것이 아니라 Memory에 데이터를 저장하였다가 불러다 쓰는 것을 의미한다.
* Enterprise급 Application에서는 DBMS의 부하를 줄이고, 성능을 높이기 위해 캐시(Cache)를 사용한다.
* 원하는 데이터가 캐시에 존재할 경우 해당 데이터를 반환하며, 이러한 상황을 Cache Hit라고 한다
* 원하는 데이터가 캐시에 존재하지 않을 경우 DBMS 또는 서버에 요청을 해야하며 이를 Cache Miss라고 한다.
* 캐시는 저장공간이 작기 때문에 지속적으로 Cache Miss가 발생하는 데이터의 경우 캐시 전략에 따라서 저장중인 데이터 구조를 변경해야 한다.

> 관련 용어
1. origin <br>
origin 혹은 origin server 는 캐시에 저장할 실 데이터가 존재하는 공간이다. <br> 
웹 캐시라면 DB 서버일 수도 있고, SW 내에서라면 파일 혹은 실행 함수 그 자체일 수도 있다. <br>

2. cache expire <br>
프로세스 내에서 사용하는 인메모리 캐시나 영구히 상주해야하는 정보를 가진 캐시가 아니라면 Cache 는 Expire Date 를 갖고 있으며 해당 시간이 지나면 상한(Stale) 상태가 된다. <br>

3. cache freshness <br>
캐시가 만료되지 않은 경우를 fresh 한 캐시라 하고 만료된 경우 stale cache라 한다. <br>

4. cache hit ratio <br>
적중률로 전체 참조 횟수 대비 Cache hit 된 비율을 의미한다. <br>
실질적으로 캐시의 설계는 Cache hit Ratio 를 높이는 데 초점을 둔다. <br>

## Redis
* 고성능 키-값 저장소로서 문자열, 리스트, 해시, 셋, 정렬된 셋 형식의 데이터를 지원하는 NoSQL
* Redis는 Memcached와 비슷한 캐시 시스템으로서 동일한 기능을 제공하면서 영속성, 다양한 데이터 구조와 같은 부가적인 기능을 지원
* 모든 데이터를 메모리에 저장하고 조회 (인메모리 데이터베이스)
* 빠른 성능 / 다양한 자료구조

![image](https://user-images.githubusercontent.com/77544214/157219857-dcd97380-c7a9-4ce5-ac81-786cff8e6529.png) 

* 레디스는 지속성을 보장하기 위해 데이터를 DISK에 저장할 수 있다. 서버가 내려가더라도 DISK에 저장된 데이터를 읽어서 메모리에 로딩한다.
* RDB(Snapshotting) 방식
-- 순간적으로 메모리에 있는 내용을 DISK에 전체를 옮겨 담는 방식
* AOF (Append On File) 방식
-- Redis의 모든 write/update 연산 자체를 모두 log 파일에 기록하는 형태
