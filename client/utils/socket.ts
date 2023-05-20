import { Socket, io } from "socket.io-client";
import { ServerToClientEvents, ClientToServerEvents } from '../../shared/types/socket'
import { Server, Signal, SocketEvent } from "../../shared/types";

/*const socket: Socket<ServerToClientEvents, ClientToServerEvents> = io('http://192.168.1.69:3000', {
    autoConnect: false,
    timeout: 5000,
}) */

const socket: Socket<ServerToClientEvents, ClientToServerEvents> = io('http://103.161.97.57:3000', {
    autoConnect: false,
    timeout: 5000,
})

export default socket

export const getPreKeyBundle = async (address: Signal.Types.SignalProtocolAddress): Promise<Signal.Types.PreKeyBundle> => new Promise((resolve, reject) => {
    socket.emit('getPreKeyBundle', address, (preKeyBundle) => {
        if (preKeyBundle === null) reject(new Error('not-found-key'))
        resolve(preKeyBundle)
    })
})

export const getAddresses = async (e164: string): Promise<Array<Signal.Types.SignalProtocolAddress>> => new Promise((resolve, reject) => {
    socket.emit('getAddresses', e164, (addresses) => {
        resolve(addresses)
    })
})

