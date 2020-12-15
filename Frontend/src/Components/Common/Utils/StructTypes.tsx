/* Used for Register */
export type UserStruct = {
    name:string,
    email:string,
    address:string,
    institutionId:number,
    password:string,
    confirmation:string
}

export type CallStruct = {
    id: number,
    title: string,
    sponsorId: number
    sponsor: string,
    description: string,
    requirements: string,
    funding: number,
    openingDate: string,
    closingDate: string,
    // not sure if necessary
    createdBy?: number,
    createdAt?: string,
    updatedBy?: number,
    updatedAt?: string
}

export type DataItemStruct = {
    id: number,
    name: string,
    type: string
}

export type PanelStruct = {
    id: number,
    name: string
}
