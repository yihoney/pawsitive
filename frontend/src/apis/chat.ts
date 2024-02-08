import { publicRequest } from '@src/hooks/requestMethods'
import { CreateChatRoomParamsType } from '@src/types/chatType' // 현재 사용자 채팅 리스트 전체 조회

// 현재 사용자 채팅 리스트 전체 조회
export const fetchChatRooms = async (userNo: number) => {
  return publicRequest.get(`/chatrooms?userNo=${userNo}`).then(res => res.data)
}

// 채팅방 생성
export const createChatRoom = async (
  chatRoomParams: CreateChatRoomParamsType,
) => {
  console.log(chatRoomParams)
  return publicRequest.post(`/chatrooms`, chatRoomParams).then(res => res.data)
}