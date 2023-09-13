# JWT 토큰 서버
![myshop_auth](https://github.com/wjh2012/myshop-authz/assets/57653698/72e75679-c20b-4730-bf1e-64f9b58d144e)
## 주 기능
- Access Token과 Refresh Token 발급
- Access Token 재발급 요청이 오면, Refresh Token 검증 후 재발급, Refresh Token 갱신

## 주요 컴포넌트
### Token Service
- Access Token, Refresh Token을 생성
- Refresh Token 검증
  
### Refresh Service
- Refresh 조회
- Refresh 저장
 
### Key
- private key로 토큰에 서명
- KeyProvideController로 public key 배포(JWK)

## 🚀배포
- CI : github
- CD : JENKINS
- AWS EC2
- Docker
